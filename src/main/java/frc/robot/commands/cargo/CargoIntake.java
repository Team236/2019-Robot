/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CargoIntake extends Command {
  public CargoIntake() {
    requires(Robot.cargo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (!Robot.cargo.haveCargo()) {
      Robot.cargo.setSpeed(RobotMap.CargoMap.SPEED_INTAKE);
    } else if (Robot.cargo.haveCargo()) {
      Robot.cargo.retract();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.cargo.haveCargo();
    //return (Robot.cargo.haveCargo()) || 
    //((Robot.drive.getAveDist()>=RobotMap.DriveMap.PLATFORM_DIST) &&
    // (Robot.oi.controller.rightPress.get() || Robot.oi.controller.leftPress.get()));
  }

  @Override
  protected void end() {
    Robot.cargo.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
