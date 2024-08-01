package com.redis.lettucemod.output;

import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.output.CommandOutput;

import java.nio.ByteBuffer;

import com.redis.lettucemod.search.AggregateResults;

public class AggregateOutput<K, V, R extends AggregateResults<K>> extends CommandOutput<K, V, R> {

    private final AggregateResultOutput<K, V> nested;
    protected int mapCount = -1;

    public AggregateOutput(RedisCodec<K, V> codec, R results) {
        super(codec, results);
        nested = new AggregateResultOutput<>(codec);
    }

    @Override
    public void set(ByteBuffer bytes) {
        nested.set(bytes);
    }

    @Override
    public void complete(int depth) {
        if (nested.isComplete()) {
            output.add(nested.getAndClear());
        }
    }

    @Override
    public void set(long integer) {
        output.setCount(integer);
    }

    @Override
    public void multi(int count) {
        if (mapCount == -1) {
            mapCount = count - 1;
        } else {
            nested.multi(count);
        }
    }

}
