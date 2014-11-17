package lambdas;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <IN>
 * @param <RET>
 */
@FunctionalInterface
public interface SingleParameterWithReturn<IN , RET>{

	public abstract RET apply(IN input);
}
