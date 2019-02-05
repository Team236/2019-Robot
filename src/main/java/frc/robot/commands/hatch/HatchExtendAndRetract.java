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
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
    if (Robot.prevExtend) {
      Robot.hatch.Retract();
    } else if (Robot.prevRetract) {
      Robot.hatch.Extend();
    }
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
  }

  @Override
  protected void interrupted() {
    end();
  }
}
