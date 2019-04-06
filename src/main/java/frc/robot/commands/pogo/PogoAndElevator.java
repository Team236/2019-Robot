/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pogo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*public class PogoAndElevator extends Command {
  double leftPosition, rightPosition, elevatorPosition;
  double leftSpeed, rightSpeed, elevatorSpeed;

  public PogoAndElevator() {
    requires(Robot.pogo);
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
    Robot.pogo.resetEncodersAtTop();
    Robot.elevator.resetEncoder();;


    // Robot.pogo.setPogoSpeed(leftSpeed, rightSpeed);
    // Robot.elevator.setSpeed(elevatorSpeed);
  }

  @Override
  protected void execute() {
    leftPosition = Math.abs(Robot.pogo.getLeftPogoDistance());
    rightPosition = Math.abs(Robot.pogo.getRightPogoDistance());
    elevatorPosition = Math.abs(Robot.elevator.getHeight());

    if (leftPosition > (rightPosition + 1)) {
      leftSpeed = 0;
    } else {
      leftSpeed = RobotMap.PogoMap.LEFT_EXTEND_SPEED;
    } if (rightPosition > (leftPosition + 1)) {
      rightSpeed = 0;
    } else {
      rightSpeed = RobotMap.PogoMap.RIGHT_EXTEND_SPEED;
    }

    // > leftPosition || elevatorPosition > rightPosition
    if (elevatorPosition > (Robot.pogo.getAvgDist() + 1)) {
      elevatorSpeed = 0;
    } else {
      elevatorSpeed = RobotMap.ElevatorMap.ENDGAME_SPEED;
    }

    Robot.pogo.setPogoSpeed(leftSpeed, rightSpeed);
    Robot.elevator.setSpeed(elevatorSpeed);
  }

  @Override
  protected boolean isFinished() {
    return Robot.pogo.atBottom() && Robot.elevator.getEncoder() >= RobotMap.ElevatorMap.ENDGAME_POSITION;
  }

  @Override
  protected void end() {
    Robot.pogo.stopPogo();
    Robot.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}*/
