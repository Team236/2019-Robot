/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoExtendAndRetract extends Command {
  // TODO: new logic needs to be tested
  public CargoExtendAndRetract() {
    // requires(Robot.cargo);
  }

  @Override
  protected void initialize() {
    // Robot.cargo.extend();
    if (Robot.cargo.isExtended()) {
      Robot.cargo.retract();
    } else if (!Robot.cargo.isExtended()) {
      if (Robot.elevator.atBottom() || Robot.oi.controller.back.get()) {
        Robot.cargo.extend();
      }
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
    Robot.cargo.retract();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
