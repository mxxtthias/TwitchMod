package tv.twitch.android.mod.emotes;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tv.twitch.android.mod.bridges.ApiCallback;
import tv.twitch.android.mod.models.Emote;
import tv.twitch.android.mod.models.EmoteSet;
import tv.twitch.android.mod.utils.Logger;

public abstract class BaseEmoteSet<T> extends ApiCallback<T> implements EmoteSet {
    private final Map<String, Emote> mEmoteMap = Collections.synchronizedMap(new LinkedHashMap<String, Emote>());

    @Override
    public synchronized void addEmote(Emote emote) {
        if (emote == null) {
            Logger.error("emote is null");
            return;
        }
        if (TextUtils.isEmpty(emote.getCode())) {
            Logger.error("Empty code");
            return;
        }

        mEmoteMap.put(emote.getCode(), emote);
    }

    @Override
    public Emote getEmote(String name) {
        return mEmoteMap.get(name);
    }

    @Override
    public List<Emote> getEmotes() {
        return new ArrayList<>(mEmoteMap.values());
    }

    @Override
    public void onRequestFail(FailReason reason) {
        Logger.error(reason.name());
    }

    @Override
    public boolean isEmpty() {
        return mEmoteMap.isEmpty();
    }
}
