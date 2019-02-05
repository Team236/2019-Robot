/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Pogo extends Subsystem {
  public TalonSRX extendMotor, rollMotor;
  private DigitalInput topLimit, bottomLimit;

  public Pogo() {
    extendMotor = new TalonSRX(RobotMap.PogoMap.ID_EXTEND_MOTOR);
    rollMotor = new TalonSRX(RobotMap.PogoMap.ID_ROLL_MOTOR); // switch to victor spx

    topLimit = new DigitalInput(RobotMap.PogoMap.DIO_TOP_LIMIT);
    bottomLimit = new DigitalInput(RobotMap.PogoMap.DIO_BOTTOM_LIMIT);
  }

  public boolean atTop() {
    return !topLimit.get();
  }

  public boolean atBottom() {
    return !bottomLimit.get();
  }

  public void setPogoSpeed(double pogoSpeed) {
    if (atTop() && pogoSpeed > 0) {
      pogoSpeed = 0;
    } else if (atBottom() && pogoSpeed < 0) {
      pogoSpeed = 0;
    } 
    extendMotor.set(ControlMode.PercentOutput, pogoSpeed);
  }

  public void stopPogo() {
    setPogoSpeed(0);
  }

  public void setRollSpeed(double rollSpeed) {
    rollMotor.set(ControlMode.PercentOutput, rollSpeed);
  }

  public void stopRoll() {
    setRollSpeed(0);
  }

  public double getPogoDistance() {
    return extendMotor.getSelectedSensorPosition(0) * RobotMap.PogoMap.DISTANCE_PER_PULSE;
  }

  public void resetEncoder() {
    extendMotor.setSelectedSensorPosition(0, 0, 0);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
