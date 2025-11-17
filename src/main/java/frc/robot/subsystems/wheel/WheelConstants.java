package frc.robot.subsystems.wheel;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class WheelConstants {
    private static final int MOTOR_ID = 1;
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    static final boolean FOC_ENABLED = true;
    static final double EJECT_VOLTAGE =-6;
    static final double COLECT_VOLTAGE =6;
    
    static {
        final TalonFXConfiguration config =new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        config.Feedback.SensorToMechanismRatio = 1.5;
        MOTOR.getConfigurator().apply(config);
    }
}
