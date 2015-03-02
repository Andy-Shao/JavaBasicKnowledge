package lambdas;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <IN1> input type one
 * @param <IN2> input type two
 * @param <RET> return type
 */
@FunctionalInterface
public interface TwoParametersWithReturn<IN1 , IN2 , RET>{

	public abstract RET apply(IN1 input1 , IN2 input2);	
}
