/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  // TODO: test extend and retract w/ 1 button
  public DoubleSolenoid hatchSolenoid, hatchScoreSolenoid;

  public Hatch() {
    hatchSolenoid = new DoubleSolenoid(RobotMap.HatchMap.SOL_FWD, RobotMap.HatchMap.SOL_REV);
    hatchScoreSolenoid = new DoubleSolenoid(RobotMap.HatchMap.SCORE_SOL_FWD, RobotMap.HatchMap.SCORE_SOL_REV);
  }

  public void Extend() {
    hatchSolenoid.set(Value.kForward);
  }

  public void Retract() {
    hatchSolenoid.set(Value.kReverse);
  }

  public void Eject() {
    hatchScoreSolenoid.set(Value.kForward);
  }

  public void Return() {
    hatchScoreSolenoid.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
  }
}
