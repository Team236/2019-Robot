/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
// import frc.robot.commands.pogo.Roll;

public class NewEndgame extends CommandGroup {
  /**
   * Add your docs here.
   */
  public NewEndgame(int level) {
    if (level == 3) {
      // brings elevator down to start height
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV3_START, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

      addSequential(new EngageClutch());

      // brings elevator and pogos down to lift robots
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV3_END, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

      addParallel(new NewPogoRoll(1));

      // continues holding robot up while it rolls forward
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV3_END, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

      // addSequential(new ElevatorToHeight(5, 2, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    } else if (level == 2) {
      // brings elevator down to start height
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV2_START, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

      addSequential(new EngageClutch());

      // brings elevator and pogos down to lift robots
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV2_END, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

      addParallel(new NewPogoRoll(1));

      // continues holding robot up while it rolls forward
      addSequential(new ElevatorToHeight(RobotMap.PogoMap.LV2_END, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

      // addSequential(new ElevatorToHeight(5, 2, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    }
  }
}
