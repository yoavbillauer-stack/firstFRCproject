package frc.robot.subsystems.wheel;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.core.CoreCANrange;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wheel extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(WheelConstants.FOC_ENABLED);
    private final TalonFX motor = WheelConstants.MOTOR;

    public Wheel() {
    }

    void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
}