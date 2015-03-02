package lambdas;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <IN> input type
 * @param <RET> return type
 */
@FunctionalInterface
public interface SingleParameterWithReturn<IN , RET>{

	public abstract RET apply(IN input);
}
