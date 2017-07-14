package vsec.com.yupax.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
