/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DisengageClutch extends Command {
  public DisengageClutch() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // if (Robot.elevator.getHeight() <= 24.25) {
      Robot.elevator.disengageClutch();

    // }
  }

  @Override
  protected boolean isFinished() {
    // if (Robot.elevator.isClutch()) {
      return true;
    // } else {
      // return false;
    // }
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
