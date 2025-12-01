package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }

    void moveToAngle(double targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    double calculatePIDOutput(double targetAngle) {
        return ArmConstants.PID_CONTROLLER.calculate(getCurrentAngleRotations(), targetAngle);
    }

    double getCurrentAngleRotations() {
        return ArmConstants.ANGLE_ENCODER_POSITION_SIGNAL.refresh().getValueAsDouble();
    }

    void stop() {
        motor.stopMotor();
    }

}
