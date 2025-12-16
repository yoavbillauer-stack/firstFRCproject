package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    void stop() {
        motor.stopMotor();
    }

    void setTargetState(ArmConstants.State state) {
        setTargetAngle(state.targetAngle);
    }

    void setTargetAngle(Rotation2d targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    private double calculatePIDOutput(Rotation2d targetAngle) {
        return ArmConstants.PID_CONTROLLER.calculate(getCurrentAngle().getRotations(), targetAngle.getRotations());
    }

    private Rotation2d getCurrentAngle() {
        double rotations = ArmConstants.ANGLE_ENCODER_POSITION_SIGNAL.refresh().getValueAsDouble();
        return Rotation2d.fromRotations(rotations);
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
}