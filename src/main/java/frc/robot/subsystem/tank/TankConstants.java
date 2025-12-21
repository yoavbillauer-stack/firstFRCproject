package frc.robot.subsystem.tank;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.XboxController;

public class TankConstants {
    private static final int
            LEFT_FRONT_MOTOR_ID = 1,
            LEFT_REAR_MOTOR_ID  = 2,
            RIGHT_FRONT_MOTOR_ID = 3,
            RIGHT_REAR_MOTOR_ID  = 4;
    private static final boolean RIGHT_SIDE_INVERTED = true;
    public static final double MAX_SPEED = 1.0;
    private static final double DEADBAND = 0.05;

    static final boolean FOC_ENABLED = true;
    public static TalonFX LEFT_FRONT_MOTOR;
    public static TalonFX LEFT_REAR_MOTOR;
    public static TalonFX RIGHT_FRONT_MOTOR;
    public static TalonFX RIGHT_REAR_MOTOR;

    private static XboxController DRIVER_CONTROLLER;

    static {
        LEFT_FRONT_MOTOR  = new TalonFX(LEFT_FRONT_MOTOR_ID);
        LEFT_REAR_MOTOR   = new TalonFX(LEFT_REAR_MOTOR_ID);
        RIGHT_FRONT_MOTOR = new TalonFX(RIGHT_FRONT_MOTOR_ID);
        RIGHT_REAR_MOTOR  = new TalonFX(RIGHT_REAR_MOTOR_ID);

        configureMotor(LEFT_FRONT_MOTOR, false);
        configureMotor(LEFT_REAR_MOTOR, false);
        configureMotor(RIGHT_FRONT_MOTOR, RIGHT_SIDE_INVERTED);
        configureMotor(RIGHT_REAR_MOTOR, RIGHT_SIDE_INVERTED);
        DRIVER_CONTROLLER = new XboxController(0);
    }
    private static void configureMotor(TalonFX MOTOR, boolean inverted) {
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = inverted ? InvertedValue.CounterClockwise_Positive
                : InvertedValue.Clockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        MOTOR.getConfigurator().apply(config);
        MOTOR.optimizeBusUtilization();
    }

    public static double getForward() {
        double val = DRIVER_CONTROLLER.getLeftY();
        return Math.abs(val) < DEADBAND ? 0 : val;
    }

    public static double getRotation() {
        double val = DRIVER_CONTROLLER.getRightX();
        return Math.abs(val) < DEADBAND ? 0 : val;
    }

}
