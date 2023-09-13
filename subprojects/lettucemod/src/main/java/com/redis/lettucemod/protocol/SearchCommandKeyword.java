package com.redis.lettucemod.protocol;

import io.lettuce.core.protocol.ProtocolKeyword;

import java.nio.charset.StandardCharsets;

public enum SearchCommandKeyword implements ProtocolKeyword {

    ADD, MAXTEXTFIELDS, TEMPORARY, NOOFFSETS, NOHL, NOFIELDS, NOFREQS, STOPWORDS, SCHEMA, TEXT, WEIGHT, NUMERIC, GEO,
    PHONETIC, TAG, SEPARATOR, SORTABLE, NOSTEM, NOINDEX, LANGUAGE, PAYLOAD, IF, FIELDS,
    NOCONTENT, VERBATIM, NOSTOPWORDS, FUZZY, WITHPAYLOADS, WITHSORTKEYS, WITHSCORES, MAX, LIMIT, SORTBY, ASC, DESC,
    INCR, DD, LOAD, PARAMS, APPLY, AS, FILTER, GROUPBY, REDUCE, COUNT, COUNT_DISTINCT, COUNT_DISTINCTISH, SUM, MIN, AVG,
    STDDEV, QUANTILE, TOLIST, FIRST_VALUE, RANDOM_SAMPLE, BY, INKEYS, INFIELDS, RETURN, HIGHLIGHT, TAGS, WITHCURSOR,
    MAXIDLE, READ, DEL, ON, PREFIX, LANGUAGE_FIELD, SCORE, SCORE_FIELD, PAYLOAD_FIELD, SKIPINITIALSCAN, GEOFILTER,
    EXPANDER, SCORER, FRAGS, LEN, SUMMARIZE, SLOP, INORDER, CASESENSITIVE, UNF, TIMEOUT, WITHSUFFIXTRIE, DIALECT,
    VECTOR, FLAT, TYPE, FLOAT32, FLOAT64, DIM, DISTANCE_METRIC, L2, IP, COSINE, INITIAL_CAP, BLOCK_SIZE,
    HNSW, M, EF_CONSTRUCTION, EF_RUNTIME, EPSILON, ;

    final byte[] bytes;

    SearchCommandKeyword() {
        bytes = name().getBytes(StandardCharsets.US_ASCII);
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }
}
