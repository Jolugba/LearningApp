package com.tinuade.learningapp.views.fragments.playvideo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.Lesson
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos
import com.tinuade.learningapp.utils.AppConstants.LESSON
import com.tinuade.learningapp.utils.Message
import com.tinuade.learningapp.utils.ViewExtension.showMessage
import kotlinx.android.synthetic.main.fragment_play_lesson.*

class PlayLessonFragment : Fragment(), Player.EventListener {

    private val viewModel: PlayVideoViewModel by viewModels()
    private var exoPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var url: String? = null
    private var lessonTitle: String? = null
    private var subjectName: String? = null
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val lessonItem = bundle.getParcelable<Lesson>(LESSON)
            if (lessonItem != null) {
                url = lessonItem.media_url
                subjectName = lessonItem.name
                id = lessonItem.id

                lessonSubject.text = subjectName

                //play url using exoplayer
                initializePlayer()

            } else {
                video_view.showMessage(Message("No result found", true))
            }
        }
        backArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(context, "exoplayer")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        exoPlayer?.playbackParameters = PlaybackParameters(1.2f)
        exoPlayer!!.addListener(this)
        video_view.player = exoPlayer

        val mediaSource = buildMediaSource(Uri.parse(url))
        exoPlayer!!.playWhenReady = playWhenReady
        exoPlayer!!.prepare(mediaSource, false, false)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_IDLE -> {
            }
            ExoPlayer.STATE_READY -> {
                subjectName?.let {
                    lessonTitle?.let { it1 ->
                        url?.let { it2 ->
                            RecentlyWatchedVideos(
                                id = id!!, mediaUrl = it2, subjectName = it,
                                it1
                            )
                        }
                    }
                }
                    ?.let {
                        viewModel.addRecentWatched(
                            it
                        )
                    }
            }
            ExoPlayer.STATE_BUFFERING -> {
            }
            ExoPlayer.STATE_ENDED -> {

            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT < 24 || exoPlayer == null) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || exoPlayer == null) {
            initializePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT < 24 || exoPlayer == null) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        if (exoPlayer != null) {
            playWhenReady = exoPlayer!!.playWhenReady
            exoPlayer!!.removeListener(this)
            exoPlayer!!.release()
            exoPlayer = null
        }
    }


}