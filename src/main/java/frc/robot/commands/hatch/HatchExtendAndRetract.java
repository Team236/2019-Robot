/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchExtendAndRetract extends Command {

  public HatchExtendAndRetract() {
    // may not want requires statement (might prevent scoring in 1 position)
    // also will have to decide which goes in init vs end (may want to flip them)
    // requires(Robot.hatch);
  }

  @Override
  protected void initialize() {
    Robot.hatch.Extend();
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
    Robot.hatch.Retract();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
