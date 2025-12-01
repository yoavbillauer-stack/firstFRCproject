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
    static void configureEncoder() {
        final CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();
        cancoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        ENCODER.getConfigurator().apply(cancoderConfig);
        ANGLE_ENCODER_POSITION_SIGNAL.setUpdateFrequency(100);
        ENCODER.optimizeBusUtilization();
    }
    static void configureMotor() {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        MOTOR.getConfigurator().apply(config);
        MOTOR.optimizeBusUtilization();

    }
}