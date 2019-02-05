/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TiltFrontCamera extends Command {

  double position;

  public TiltFrontCamera() {
  }

  public TiltFrontCamera(double _position) {
    this.position = _position;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.camServo2.set(position);
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
