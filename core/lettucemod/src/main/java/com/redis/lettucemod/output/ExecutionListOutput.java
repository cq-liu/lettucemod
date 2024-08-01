package com.redis.lettucemod.output;

import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.output.ListSubscriber;
import io.lettuce.core.output.StreamingOutput;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import com.redis.lettucemod.gears.Execution;

public class ExecutionListOutput<K, V> extends CommandOutput<K, V, List<Execution>> implements StreamingOutput<Execution> {

    private boolean initialized;
    private Subscriber<Execution> subscriber;
    private Execution execution;
    private String field;

    public ExecutionListOutput(RedisCodec<K, V> codec) {
        super(codec, Collections.emptyList());
        setSubscriber(ListSubscriber.instance());
    }

    @Override
    public void set(ByteBuffer bytes) {
        if (field == null) {
            field = decodeAscii(bytes);
            return;
        }
        if (fieldEquals("executionId")) {
            execution = new Execution();
            execution.setId(decodeAscii(bytes));
            field = null;
            return;
        }
        if (fieldEquals("status")) {
            execution.setStatus(decodeAscii(bytes));
            field = null;
        }
    }

    private boolean fieldEquals(String name) {
        return name.equals(field);
    }

    @Override
    public void set(long integer) {
        if (fieldEquals("registered")) {
            execution.setRegistered(integer);
            field = null;
            subscriber.onNext(output, execution);
            execution = null;
        }
    }

    @Override
    public void multi(int count) {
        if (!initialized) {
            output = OutputFactory.newList(count);
            initialized = true;
        }
    }

    public void setSubscriber(Subscriber<Execution> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Subscriber<Execution> getSubscriber() {
        return subscriber;
    }

}
