package lambdas;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <IN1>
 * @param <IN2>
 * @param <RET>
 */
@FunctionalInterface
public interface TwoParametersWithReturn<IN1 , IN2 , RET>{

	public abstract RET apply(IN1 input1 , IN2 input2);	
}
