/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import limelightLib.LimeLight;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {

  private LimeLight limelight;

  public Limelight() {
    limelight = new LimeLight("limelight");
  }

  @Override
  public void initDefaultCommand() {
  }

  public LimeLight getLimeLight() {
    return limelight;
  }

  public boolean isTarget() {
    return getLimeLight().getIsTargetFound();
  }

  public double getAngleOffset() {
    return getLimeLight().getdegRotationToTarget();
  }

  public double getTargetArea() {
    return getLimeLight().getTargetArea();
  }
}
