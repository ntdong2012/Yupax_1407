package vsec.com.yupax.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */


@Qualifier
@Documented
@Retention(RUNTIME)
public @interface YupaxUrl {

}
