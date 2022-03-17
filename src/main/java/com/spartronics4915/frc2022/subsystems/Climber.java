package com.spartronics4915.frc2022.subsystems;

import static com.spartronics4915.frc2022.Constants.Climber.*;
import com.spartronics4915.frc2022.Constants;

import com.spartronics4915.lib.subsystems.SpartronicsSubsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Detailed description of Climber.
 */
public class Climber extends SpartronicsSubsystem
{
    // The subsystem's hardware is defined here...
    private TalonFX mClimberMotor;
    private Solenoid mClimberSolenoid;
    private TalonFXSensorCollection mMotorSensors;

    /** Creates a new Climber. */
    public Climber()
    {
        boolean success = true;
        try
        {
            // ...and constructed here.
            mClimberMotor = new TalonFX(kClimberMotorId);
            mClimberMotor.setInverted(kMotorIsInverted);
            mClimberMotor.setNeutralMode(NeutralMode.Brake); // set brake mode

            //mMotorSensors = new TalonFXSensorCollection()

            mClimberSolenoid = new Solenoid(Constants.kPCMId, PneumaticsModuleType.CTREPCM, kClimberSolenoidId);
        }
        catch (Exception exception)
        {
            logException("Could not construct hardware: ", exception);
            success = false;
        }
        logInitialized(success);
        
        mClimberMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, kMaxCurrent, kMaxCurrent, 0));
        mClimberMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
    }

    // Subsystem methods - actions the robot can take - should be placed here.
    public void setMotor(double speed)
    {
        mClimberMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    public void setSolenoid(boolean isExtended)
    {
        // mClimberMotor.get
        mClimberSolenoid.set(isExtended != kSolenoidIsInverted);
    }

    public double getCurrentRotations(){
        return mClimberMotor.getSensorCollection().getIntegratedSensorPosition() / kNativeUnitsPerRevolution;
    }

    public boolean isRotatedTooMuch(){
        return (getCurrentRotations() >= kMaxRotations);
    }
    
    public boolean isRotatedTooLittle(){
        return (getCurrentRotations() <= kMinRotations);
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {}

    /** This method will be called once per scheduler run during simulation. */
    @Override
    public void simulationPeriodic() {}

    public void outputTelemetry() {}
}
