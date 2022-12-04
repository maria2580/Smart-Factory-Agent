package org.primitive.Network.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginVO {
       public String ID;
       public String PW;
       public int env;
}