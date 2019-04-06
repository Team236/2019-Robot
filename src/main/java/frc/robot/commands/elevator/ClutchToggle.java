/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClutchToggle extends Command {
  public ClutchToggle() {
  }

  @Override
  protected void initialize() {
    // Robot.elevator.engageClutch();
    Robot.elevator.disengageClutch();

  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    // Robot.elevator.disengageClutch();
    Robot.elevator.engageClutch();

  }

  @Override
  protected void interrupted() {
    end();
  }
}
