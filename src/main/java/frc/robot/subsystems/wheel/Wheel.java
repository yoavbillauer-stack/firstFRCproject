package frc.robot.subsystems.wheel;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wheel extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(WheelConstants.FOC_ENABLED);
    private final TalonFX motor = WheelConstants.MOTOR;

    public Wheel() {
    }
    public void eject(){
        motor.setVoltage(WheelConstants.EJECT_VOLTAGE);
    }
    public void colect(){
        motor.setVoltage(WheelConstants.COLECT_VOLTAGE);
    }

    void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
}