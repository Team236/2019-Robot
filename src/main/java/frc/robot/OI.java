/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.GyroDrive;
import frc.robot.commands.camera.TiltFrontCamera;
import frc.robot.commands.camera.TiltRearCamera;
import frc.robot.commands.cargo.CargoEject;
import frc.robot.commands.cargo.CargoExtend;
import frc.robot.commands.cargo.CargoExtendAndRetract;
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
import lib.oi.triggers.TwoButton;
import lib.oi.triggers.JoystickPOV.Direction;
import lib.pid.PIDParameters;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {   
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

    // DRIVE
    controller.rb.whileHeld(new DriveWithThumbsticks());
    rightStick.right.whileHeld(new GyroDrive(RobotMap.AutoMap.GYRO_DRIVE_KP, 240, .25));

    // POGO
    // TODO: button for auto endgame
    // controller.back.whileHeld(new PogoWithThumbstick()); // right is forward
    // controller.y.whileHeld(new PogoExtend());
    // controller.b.whileHeld(new PogoRetract());
    // controller.a.whileHeld(new Roll());

    // CARGO
    leftStick.middle.whileHeld(new CargoIntake());
    leftStick.trigger.whileHeld(new CargoEject());
    controller.lb.toggleWhenPressed(new CargoExtendAndRetract());

    // HATCH
    rightStick.trigger.whenPressed(new HatchScore());
    // controller.a.whenPressed(new HatchExtend());
    // controller.b.whenPressed(new HatchRetract());
    controller.rb.toggleWhenPressed(new HatchExtendAndRetract());

    // ELEVATOR
    // controller.start.whileHeld(new ElevatorWithThumbstick());
    TwoButton cargo1 = new TwoButton(controller.start, controller.a);
    TwoButton cargo2 = new TwoButton(controller.start, controller.x);
    TwoButton cargo3 = new TwoButton(controller.start, controller.y);
    // H1, cargo intake, C1 (rocket) in cargo mode
    cargo1.whenPressed(new ElevatorToHeight(0 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    controller.a.whenPressed(new ElevatorToHeight(0, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // only used in cargo mode, C1.5 (cargo ship)
    controller.b.whenPressed(new ElevatorToHeight(12.5, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H2, C2 in cargo mode
    cargo2.whenPressed(new ElevatorToHeight(28 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    controller.x.whenPressed(new ElevatorToHeight(28, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H3, C3 in cargo mode
    cargo3.whenPressed(new ElevatorToHeight(56 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    controller.y.whenPressed(new ElevatorToHeight(56, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

    // CAMERA - rotating on servo
    JoystickPOV raiseFrontCam = new JoystickPOV(rightStick, Direction.UP);
    JoystickPOV lowerFrontCam = new JoystickPOV(rightStick, Direction.DOWN);
    JoystickPOV raiseRearCam = new JoystickPOV(leftStick, Direction.UP);
    JoystickPOV lowerRearCam = new JoystickPOV(leftStick, Direction.DOWN);
    raiseFrontCam.whenPressed(new TiltFrontCamera(0.0));
    lowerFrontCam.whenPressed(new TiltFrontCamera(1.0));
    raiseRearCam.whenPressed(new TiltRearCamera(0.0));
    lowerRearCam.whenPressed(new TiltRearCamera(1.0));

  }

}
        