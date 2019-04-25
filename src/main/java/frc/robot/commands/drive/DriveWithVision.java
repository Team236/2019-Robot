/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveWithVision extends Command {
  public DriveWithVision() {
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3.0);
    // establishes limelight pipeline
    Robot.limelight.getLimeLight().setPipeline(0);
    SmartDashboard.putNumber("CameraMode", Robot.limelight.getLimeLight().getPipelineLatency());
    SmartDashboard.putNumber("Pipeline", Robot.limelight.getLimeLight().getPipeline());
  }

  @Override
  protected void execute() {
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3.0);

    // speed to be used
    final double speed = .2;
    double angleOffset = Robot.limelight.getAngleOffset();
    double targetArea = Robot.limelight.getTargetArea();
    SmartDashboard.putNumber("vision angleOffset", angleOffset);


    // TODO: consider adding while (distance > x) do to stop robot
    // TODO: use turn pid to correct anlge

    // robot needs to turn right
    if (angleOffset > 2.7) {
      Robot.drive.setLeftSpeed(speed);
      Robot.drive.setRightSpeed(-speed);

      // Robot.drive.setLeftSpeed(speed);
      // Robot.drive.setRightSpeed(speed * angleOffset);
    }

    // robot needs to turn left
    if (angleOffset < -2.7) {
      Robot.drive.setLeftSpeed(-speed);
      Robot.drive.setRightSpeed(speed);

      // Robot.drive.setLeftSpeed(speed * angleOffset);
      // Robot.drive.setRightSpeed(speed);
    }

    // executes if target found
    if (Robot.limelight.isTarget()) {
      if (angleOffset > -2.7 && angleOffset < 2.7) {
        // go straight
        Robot.drive.setLeftSpeed(.5);
        Robot.drive.setRightSpeed(.5);

        // begin progressive slowdown
        /* if (targetArea == 0.0) {
          Robot.drive.stop();
        }

        boolean hasRun = false;
        if (targetArea >= 4.7) {
          if (!hasRun) {
            Robot.drive.setLeftSpeed(.5);
            Robot.drive.setRightSpeed(.7);
            if (targetArea == 0.0) {
              Robot.drive.stop();
            }
          }
          hasRun = true;
        } */

        /* if ((targetArea >= 2.0 && targetArea <= 6.5) || Robot.limelight.getLimeLight().getdegVerticalToTarget() <= -16) {
          // proportionalize percent area to speed (as percent gets larger speed gets smaller)
          Robot.drive.setLeftSpeed(1 / targetArea * 2);
          Robot.drive.setRightSpeed(1 / targetArea * 2);
        } */

        /* if (Robot.limelight.getLimeLight().getdegVerticalToTarget() <= -16) {
          Robot.drive.setLeftSpeed(1 / targetArea * 2);
          Robot.drive.setRightSpeed(1 / targetArea * 2);
        } */
        // end progressive slowdown
      }
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drive.stop();
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1.0);

  }

  @Override
  protected void interrupted() {
    end();
  }
}
