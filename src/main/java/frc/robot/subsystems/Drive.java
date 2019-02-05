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

/**
 * 
 */
public class Drive extends Subsystem {
  public TalonSRX leftMaster, rightMaster;
  public VictorSPX leftMiddleSlave, leftRearSlave;
  public VictorSPX rightMiddleSlave, rightRearSlave;

  public AHRS navx;

  public Drive() {
    leftMaster = new TalonSRX(RobotMap.DriveMap.ID_LEFT_FRONT);
    rightMaster = new TalonSRX(RobotMap.DriveMap.ID_RIGHT_FRONT);

    leftMiddleSlave = new VictorSPX(RobotMap.DriveMap.ID_LEFT_MIDDLE);
    leftRearSlave = new VictorSPX(RobotMap.DriveMap.ID_LEFT_REAR);
    rightMiddleSlave = new VictorSPX(RobotMap.DriveMap.ID_RIGHT_MIDDLE);
    rightRearSlave = new VictorSPX(RobotMap.DriveMap.ID_RIGHT_REAR);

    leftMiddleSlave.follow(leftMaster);
    leftRearSlave.follow(leftMaster);
    rightMiddleSlave.follow(rightMaster);
    rightRearSlave.follow(rightMaster);

    navx = new AHRS(SPI.Port.kMXP);
  }

  public void setLeftSpeed(double speed) {
    leftMaster.set(ControlMode.PercentOutput, speed);
  }

  public void setRightSpeed(double speed) {
    rightMaster.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    setLeftSpeed(0);
    setRightSpeed(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());

  }
}
