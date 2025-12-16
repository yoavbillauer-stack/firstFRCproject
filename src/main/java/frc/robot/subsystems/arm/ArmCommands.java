package frc.robot.subsystems.arm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetStateCommand(ArmConstants.State state){
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetState(state),
                () -> RobotContainer.ARM.setTargetState(state),
                (interrupted) -> RobotContainer.ARM.stop(),
                ()-> false
        );
    }

    public static Command getSetTargetAngleCommand(Rotation2d targetAngle){
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                (interrupted) -> RobotContainer.ARM.stop(),
                ()-> false
        );
    }
}