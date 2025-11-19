package frc.robot.subsystems.arm;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;

public class ArmConstants {
    private static final int MOTOR_ID = 2;
    private static final int ENCODER_ID = 1;
    static final CANcoder ENCODER = new CANcoder(ENCODER_ID);
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);
    static final PIDController pid = new PIDController(kP, kI, kD);
}