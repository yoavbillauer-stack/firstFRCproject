package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.units.Units.Radians;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    void setTargetAngle(Rotation2d targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }

    private double calculatePIDOutput(Rotation2d targetAngle) {
        return ArmConstants.PID_CONTROLLER.calculate(getCurrentAngleRotations(),new Rotation2d(targetAngle));
    }

    private Rotation2d getCurrentAngleRotations() {
        return new Rotation2d(ArmConstants.ANGLE_ENCODER_POSITION_SIGNAL.refresh().getValue());
    }

    void stop() {
        motor.stopMotor();
    }

    void setTargetState(ArmConstants.State state) {
        setTargetAngle(state.targetAngle);
    }

}
