/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);
    
  }
  
}
