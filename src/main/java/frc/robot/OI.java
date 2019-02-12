/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.camera.TiltFrontCamera;
import frc.robot.commands.camera.TiltRearCamera;
import frc.robot.commands.cargo.CargoEject;
import frc.robot.commands.cargo.CargoExtend;
import frc.robot.commands.cargo.CargoIntake;
import frc.robot.commands.cargo.CargoRetract;
import frc.robot.commands.drive.DriveWithThumbsticks;
import frc.robot.commands.elevator.ElevatorToHeight;
import frc.robot.commands.elevator.ElevatorWithThumbstick;
import frc.robot.commands.hatch.HatchExtend;
import frc.robot.commands.hatch.HatchExtendAndRetract;
import frc.robot.commands.hatch.HatchRetract;
import frc.robot.commands.hatch.HatchScore;
import frc.robot.commands.pogo.PogoExtend;
import frc.robot.commands.pogo.PogoRetract;
import frc.robot.commands.pogo.PogoWithThumbstick;
import frc.robot.commands.pogo.Roll;
import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;
import lib.oi.triggers.JoystickPOV;
import lib.oi.triggers.JoystickPOV.Direction;
import lib.pid.PIDParameters;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {
    // TODO: "FINALIZE" BUTTONS
    
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

    // DRIVE
    controller.rb.whileHeld(new DriveWithThumbsticks());

    // POGO
    // controller.back.whileHeld(new PogoWithThumbstick());
    // controller.y.whenPressed(new PogoExtend());
    // controller.b.whenPressed(new PogoRetract());
    // controller.lb.whileHeld(new Roll());

    // CARGO
    // controller.a.whileHeld(new CargoIntake());
    // controller.b.whileHeld(new CargoEject());

    // controller.y.whenPressed(new CargoRetract()); // up
    // controller.x.whenPressed(new CargoExtend()); // down

    // HATCH
    // controller.x.whileHeld(new HatchScore());
    // controller.rb.whenPressed(new HatchExtendAndRetract());
    // controller.a.whenPressed(new HatchExtend());
    // controller.b.whenPressed(new HatchRetract());

    // ELEVATOR
    controller.start.whileHeld(new ElevatorWithThumbstick());
    controller.x.whenPressed(new ElevatorToHeight(56, 3, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    controller.y.whenPressed(new ElevatorToHeight(28, 3, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));


    // CAMERA
    JoystickPOV raiseFrontCam = new JoystickPOV(rightStick, Direction.UP);
    JoystickPOV lowerFrontCam = new JoystickPOV(rightStick, Direction.DOWN);
    JoystickPOV raiseRearCam = new JoystickPOV(leftStick, Direction.UP);
    JoystickPOV lowerRearCam = new JoystickPOV(leftStick, Direction.DOWN);
    // raiseFrontCam.whenPressed(new TiltFrontCamera(0.0));
    // lowerFrontCam.whenPressed(new TiltFrontCamera(1.0));
    // raiseRearCam.whenPressed(new TiltRearCamera(0.0));
    // lowerRearCam.whenPressed(new TiltRearCamera(1.0));

  }

}
