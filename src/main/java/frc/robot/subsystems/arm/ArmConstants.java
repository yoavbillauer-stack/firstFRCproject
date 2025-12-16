package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;

public class ArmConstants {
    private static final int
            MOTOR_ID = 1,
            ENCODER_ID = 1;
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);
    static final CANcoder ENCODER = new CANcoder(ENCODER_ID);

    static final boolean FOC_ENABLED = true;
    static final PIDController PID_CONTROLLER = new PIDController(1, 2, 3);
    static final StatusSignal<Angle> ANGLE_ENCODER_POSITION_SIGNAL = ENCODER.getPosition();

    static {
        configureMotor();
        configureEncoder();
    }

    private static void configureEncoder() {
        final CANcoderConfiguration config = new CANcoderConfiguration();
        config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        config.MagnetSensor.MagnetOffset = 0;
        config.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.0;
        ENCODER.getConfigurator().apply(config);
        ANGLE_ENCODER_POSITION_SIGNAL.setUpdateFrequency(100);
        ENCODER.optimizeBusUtilization();
    }

    private static void configureMotor() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        MOTOR.getConfigurator().apply(config);
        MOTOR.optimizeBusUtilization();
    }

    public enum State {
        LOW(Rotation2d.fromDegrees(15)),
        HIGH(Rotation2d.fromDegrees(45)),
        REST(Rotation2d.fromDegrees(0));

        final Rotation2d targetAngle;

        State(Rotation2d targetAngle) {
            this.targetAngle = targetAngle;
        }
    }
}