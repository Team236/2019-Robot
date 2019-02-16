/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IncrementUp extends Command {
  public IncrementUp() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
    double height = Robot.elevator.getHeight() + RobotMap.ElevatorMap.CARGO_OFFSET;
    // Scheduler.getInstance().add(new ElevatorToHeight(height, 10, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
