/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveWithJoysticks;
import lib.pid.PIDOutput;
import lib.pid.PIDSource;

/**
 * 
 */
public class Drive extends Subsystem implements PIDSource, PIDOutput {
  public TalonSRX leftMaster, rightMaster;
  public VictorSPX leftMiddleSlave; // , leftRearSlave;
  public VictorSPX rightMiddleSlave; // , rightRearSlave;

  public AHRS navx;

  public Drive() {
    leftMaster = new TalonSRX(RobotMap.DriveMap.ID_T_LEFT_FRONT);
    rightMaster = new TalonSRX(RobotMap.DriveMap.ID_T_RIGHT_FRONT);

    leftMiddleSlave = new VictorSPX(RobotMap.DriveMap.ID_V_LEFT_MIDDLE);
    // leftRearSlave = new VictorSPX(RobotMap.DriveMap.ID_V_LEFT_REAR);
    rightMiddleSlave = new VictorSPX(RobotMap.DriveMap.ID_V_RIGHT_MIDDLE);
    // rightRearSlave = new VictorSPX(RobotMap.DriveMap.ID_V_RIGHT_REAR);

    leftMiddleSlave.follow(leftMaster);
    // leftRearSlave.follow(leftMaster);
    rightMiddleSlave.follow(rightMaster);
    // rightRearSlave.follow(rightMaster);

    leftMaster.setSensorPhase(false);
    rightMaster.setSensorPhase(true);

    navx = new AHRS(SPI.Port.kMXP);
  }

  public void setLeftSpeed(double speed) {
    leftMaster.set(ControlMode.PercentOutput, -speed);
    // leftMiddleSlave.set(ControlMode.PercentOutput, speed);
  }

  public void setRightSpeed(double speed) {
    rightMaster.set(ControlMode.PercentOutput, -speed);
    // rightMiddleSlave.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    setLeftSpeed(0);
    setRightSpeed(0);
  }

  public int getLeftEncoder() {
    return leftMaster.getSelectedSensorPosition();
  }

  public int getRightEncoder() {
    return rightMaster.getSelectedSensorPosition();
  }

  public double getLeftDist() {
    return getLeftEncoder() * RobotMap.DriveMap.DISTANCE_PER_PULSE;
  }

  public double getRightDist() {
    return getRightEncoder() * RobotMap.DriveMap.DISTANCE_PER_PULSE;
  }

  public void resetEncoders() {
    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public void pidSet(double speed) {
    setLeftSpeed(speed);
    setRightSpeed(-speed);
  }

  @Override
  public double pidGet() {
    return navx.getAngle();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());

  }
}
