/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class NewEndgame extends CommandGroup {
  /**
   * Add your docs here.
   */
  public NewEndgame() {
    addSequential(new ElevatorToHeight(22, 2, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

    addSequential(new EngageClutch());

    //not sure if this is the command we want, will probably want to end at a certain height
    addSequential(new SetElevatorSpeed(-.5));
  }
}
