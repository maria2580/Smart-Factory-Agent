package org.primitive.Network.VO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import org.primitive.SensorRelates.SensorValue;
@AllArgsConstructor
@Getter
public class SensorValueDTO {
    private SensorValue[] sensorValues;
    private String ID;
}
