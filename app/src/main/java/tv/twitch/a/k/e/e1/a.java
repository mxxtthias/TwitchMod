package tv.twitch.a.k.e.e1;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import tv.twitch.a.k.e.g;
import tv.twitch.a.k.e.m0;
import tv.twitch.a.k.x.b.r.d;
import tv.twitch.android.mod.bridges.ChatMessageFactory;
import tv.twitch.android.mod.bridges.LoaderLS;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.models.webview.WebViewSource;

import static tv.twitch.android.mod.utils.ChatUtils.injectCopySpan;
import static tv.twitch.android.mod.utils.ChatUtils.injectEmotesSpan;

// Source: ChatMessageFactory
public class a implements ChatMessageFactory { // TODO: __IMPLEMENT
    static CharSequence a(a chatMessageFactory, String url, d mediaSpan, String text, m0 urlImageClickableProvider, boolean z, int i2, Object obj) {
        return null;
    }

    @Override
    public CharSequence getSpannedEmote(String url, String emoteText, boolean isGif) { // TODO: __ADD
        if (isGif)
            return a(this, url, d.valueOf("AnimatedBit"), emoteText, null, false, 24, null);
        else
            return a(this, url, d.valueOf("Emote"), emoteText, null, false, 24, null);
    }

    private final CharSequence a(tv.twitch.a.k.e.g gVar, int i2, tv.twitch.a.k.e.t0.a aVar, boolean z, String str, String str2) {
        i2 = Helper.hookUsernameSpanColor(i2); // TODO: __ADD
        return null;
    }

    private final Spanned a(tv.twitch.a.k.e.g chatMessageInterface, boolean z, boolean z2, boolean z3, int i2, int i3, Object iClickableUsernameSpanListener, Object twitchUrlSpanClickListener, WebViewSource webViewSource, String str, boolean z4, Object censoredMessageTrackingInfo, Integer num, Object eventDispatcher) {
        try {
// TODO: HOOK MESSAGE FACTORY RESULT
//          invoke-static/range {v8 .. v22}, Ltv/twitch/a/k/e/e1/a;->a(Ltv/twitch/a/k/e/e1/a;Ltv/twitch/a/k/e/g;ZZZLtv/twitch/android/models/webview/WebViewSource;Ltv/twitch/a/k/x/b/r/g;Ljava/lang/String;Ltv/twitch/a/k/e/e1/a$c;ILtv/twitch/a/k/e/o1/c;Ljava/lang/Integer;Ltv/twitch/android/core/mvp/viewdelegate/EventDispatcher;ILjava/lang/Object;)Landroid/text/SpannedString;
//          move-result-object v10
//          move-object/from16 v11, p1
//          move/from16 v12, p6
//          invoke-direct {v8, v10, v11, v12}, Ltv/twitch/a/k/e/e1/a;->hookMessageMethodResult(Landroid/text/SpannedString;Ltv/twitch/a/k/e/g;I)Landroid/text/SpannedString;
//          move-result-object v10

            SpannedString message = new SpannedString("KEKW");
            message = hookMessageMethodResult(message, chatMessageInterface, i3);

            return message;
        } catch (Throwable th) {
            th.printStackTrace();

            SpannableString error = new SpannableString("<Error processing message>");
            error.setSpan(new ForegroundColorSpan(Color.RED), 0, error.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return error;
        }
    }

    private SpannedString hookMessageMethodResult(SpannedString orgMessage, g chatMessageInterface, int channelId) {  // TODO: __ADD
        try {
            if (TextUtils.isEmpty(orgMessage)) {
                return orgMessage;
            }

            if (chatMessageInterface.a())
                return orgMessage;

            SpannedString spannedString = new SpannedString(orgMessage);
            if (LoaderLS.getInstance().getPrefManager().isEmotesOn()) {
                LoaderLS.getInstance().getEmoteManager().requestIfNeed(channelId);
                spannedString = injectEmotesSpan(spannedString, channelId, this);
            }

            if (LoaderLS.getInstance().getPrefManager().isCopyMsgOn())
                spannedString = injectCopySpan(spannedString, chatMessageInterface.e());

            return spannedString;
        } catch (Throwable th) {
            th.printStackTrace();
        }

        return orgMessage;
    }
}