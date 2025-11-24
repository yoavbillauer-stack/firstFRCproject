package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private static final boolean FOC_ENABLED = true;
    private final TalonFX motor = ArmConstants.MOTOR;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public double getAngleEncoderPositionRotation(){
        return ArmConstants.ANGLE_ENCODER_POSITION_SIGNAL.refresh().getValueAsDouble();
    }

    void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
    double requiredPower(double setPoint){
        return ArmConstants.PID_CONTROLLER.calculate(getAngleEncoderPositionRotation(), setPoint);
    }
    void theWork(double setPoint){
        setTargetVoltage(requiredPower(setPoint));
    }
}
