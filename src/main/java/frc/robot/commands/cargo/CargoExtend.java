/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoExtend extends Command {
  public CargoExtend() {
    requires(Robot.cargo);
  }

  @Override
  protected void initialize() {
    // if (Robot.elevator.atBottom() || Robot.oi.controller.back.get()) {
      Robot.cargo.extend();
    // }
    // System.out.println("cargo starting");
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
