/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  public DigitalInput leftLimit, rightLimit;

  public Elevator() {
    leftLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_LEFT_LIMIT);
    rightLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_RIGHT_LIMIT);
  }

  public boolean isLeftLimit() {
    return leftLimit.get();
  }

  public boolean isRightLimit() {
    return rightLimit.get();
  }

  @Override
  public void initDefaultCommand() {
  }
}
