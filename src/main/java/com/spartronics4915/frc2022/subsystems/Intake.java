package com.spartronics4915.frc2022.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.spartronics4915.frc2022.Constants;
import com.spartronics4915.lib.subsystems.SpartronicsSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.simulation.SimHooks;

/**
 * Detailed description of Intake.
 */
public class Intake extends SpartronicsSubsystem
{
    // The subsystem's hardware is defined here...
   //SpartronicsMotor mIntakeMotor;
    private CANSparkMax mIntakeMotor;
    private Solenoid mIntakeArm;
    private DigitalInput switchState;

    /** Creates a new Intake.
     * @param SpartronicsMax */
    public Intake()
    {
        boolean success = true;
        try
        {
            // ...and constructed here.
           mIntakeMotor = new CANSparkMax(Constants.kTestMotorId,MotorType.kBrushless);
           mIntakeArm = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.Intake.kIntakeSolenoidId);
           //switchState = new DigitalInput(0);
           //setting starting values
           //mIntakeArm.set(true);
           //mIntakeMotor.set(0);
        }
        catch (Exception exception)
        {
            logException("Could not construct hardware: ", exception);
            success = false;
        }
        logInitialized(success);
    }

    //Subsystem methods - actions the robot can take - should be placed here.
   /* public void setSpeed(double speed) {
        mIntakeMotor.set(speed);
        logInfo("running");
    }-not sure if I need this code*/ 

   public void startIntake(){
        mIntakeArm.set(true);
        showArmState();
        mIntakeMotor.set(0.3);
        //logInfo("intake running"); - not sure if we need this could be too much for driver to pay attention to
    }

    public void stopIntake() {
        mIntakeArm.set(false); 
        showArmState();
        mIntakeMotor.set(0);
        //logInfo("intake stopped"); - not sure if we need this, same as above
    }

    public void eject(){
        mIntakeMotor.set(-0.3);
    }

    public void extendPneumatics(){
        //mIntakeArm.set(true);
    }

    public void showArmState(){
        Boolean arm = mIntakeArm.get();
        logInfo("current arm state:" + arm.toString());
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        /*if(switchState.get()){
            mIntakeMotor.set(.5);
        } else {
            mIntakeMotor.set(0);
        }*/
    }

    /** This method will be called once per scheduler run during simulation. */
    @Override
    public void simulationPeriodic() {}

    public void outputTelemetry() {}
}
