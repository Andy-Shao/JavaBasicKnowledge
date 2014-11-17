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
 */
public interface SingleParameterWithoutReturn<IN>{

	public abstract void apply(IN input);
}
