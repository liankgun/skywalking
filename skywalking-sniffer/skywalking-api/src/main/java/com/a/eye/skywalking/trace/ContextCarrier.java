package com.a.eye.skywalking.trace;

import com.a.eye.skywalking.util.StringUtil;
import java.io.Serializable;

/**
 * {@link ContextCarrier} is a data carrier of {@link TracerContext}.
 * It holds the snapshot (current state) of {@link TracerContext}.
 *
 * Created by wusheng on 2017/2/17.
 */
public class ContextCarrier extends TraceSegmentRef implements Serializable {

    /**
     * Serialize this {@link ContextCarrier} to a {@link String},
     * with '|' split.
     *
     * @return the serialization string.
     */
    public String serialize() {
        return StringUtil.join('|', this.getTraceSegmentId(), this.getSpanId() + "");
    }

    /**
     * Initialize fields with the given text.
     *
     * @param text carries {@link #traceSegmentId} and {@link #spanId}, with '|' split.
     */
    public ContextCarrier deserialize(String text) {
        if(text != null){
            String[] parts = text.split("|");
            if(parts.length == 2){
                try{
                    setSpanId(Integer.parseInt(parts[1]));
                    setTraceSegmentId(parts[0]);
                }catch(NumberFormatException e){

                }
            }
        }
        return this;
    }

}
