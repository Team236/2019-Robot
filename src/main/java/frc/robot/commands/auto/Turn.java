/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import lib.pid.PID;
import lib.pid.PIDParameters;

public class Turn extends Command {
  private PID pid;
  private double degrees;
  private double margin;
  private double error;

  public Turn(double _degrees, double _margin, PIDParameters _pid) {
    requires(Robot.drive);

    this.degrees = _degrees;
    this.margin = _margin;
    
    pid = new PID(Robot.drive, Robot.drive, _pid);
  }

  @Override
  protected void initialize() {
    Robot.drive.navx.reset();

    pid.setSetpoint(degrees);

    pid.enable();
    pid.update();
  }

  @Override
  protected void execute() {
    error = pid.getError();
    SmartDashboard.putNumber("turn error", error);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(error) < margin && Math.abs(Robot.drive.navx.getRate()) < .25);
  }

  @Override
  protected void end() {
    pid.disable();
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
