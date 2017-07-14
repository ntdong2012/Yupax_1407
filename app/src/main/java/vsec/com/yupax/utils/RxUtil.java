package vsec.com.yupax.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vsec.com.yupax.model.http.response.YupaxResponse;
import vsec.com.yupax.model.http.exception.ApiException;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public class RxUtil {

    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    public static <T> FlowableTransformer<YupaxResponse<T>, T> handleResult() {
        return new FlowableTransformer<YupaxResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<YupaxResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<YupaxResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(YupaxResponse<T> tGankHttpResponse) {
                        if (tGankHttpResponse.getCode() == 200) {
                            return createData(tGankHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException("The server returns an error"));
                        }
                    }
                });
            }
        };
    }
}
