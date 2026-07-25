(require '[replicant.dom :as r])
(declare close-ahorn)
(def boss-div (js/document.getElementById "boss"))
  (let [style (js/document.createElement "style")]
    (set! (.-innerHTML style)
      "@keyframes ahornGlow {
         0% { background-position: 0% 50%; }
         50% { background-position: 100% 50%; }
         100% { background-position: 0% 50%; }
       }
       @keyframes ahornFall {
         0% { transform: translateY(-30px) rotate(0deg); opacity: 0; }
         10% { opacity: 1; }
         100% { transform: translateY(110vh) rotate(360deg); opacity: 0.9; }
       }
       @keyframes ahornPop {
         0% { transform: scale(0.3); opacity: 0; }
         70% { transform: scale(1.12); }
         100% { transform: scale(1); opacity: 1; }
       }")
    (.appendChild js/document.head style))


;;hornbox
(def bosses (atom [["https://i.pravatar.cc/150?img=1" "UserA" "Hello World!" "07-20 19:17"]
                   ["https://i.pravatar.cc/150?img=5" "UserB" "Nice to meet you" "07-22 23:03"]
                   ["https://i.pravatar.cc/150?img=8" "UserC" "Sample message here" "07-23 10:00"]]))

(def on-number (atom 0))
(defn on-boss-render [[img name text _]]
 (list [:img {:src img :style {:width "60px" :height "60px" :border-radius "50%" :animation "ahornPop 0.6s ease"}}]
       [:h2 {:style {:color "#7a4b00"}} (str "土豪 " name " 驾到")][:p {:style {:color "#5a3600"}} text]))
(def hornbox 
 [:div#hornboss {:style {:position "fixed"
                 :top "0" :left "0" :right "0" :bottom "0"
                 :background "linear-gradient(135deg, #b8860b, #ffd700, #ffec8b, #ffd700, #b8860b)"
                 :display "flex"
                         :background-size "300% 300%"
                         :animation "ahornGlow 4s ease infinite"
                 :flex-direction "column"
                 :align-items "center"
                 :justify-content "center"
                 :text-align "center"
                         :opacity "0" 
                         :transition "opacity 0.5s"}}
  [:div#hornbosses {:style {:z-index 5 :transition "opacity 0.3s"
                            :text-shadow "0 1px 0 rgba(255,255,255,0.5), 0 0 12px rgba(255,215,0,0.6)"
                            :letter-spacing "1px"}}]
  [:div {:style {:background "linear-gradient(0deg,#494,#5b5)"
                 :z-index 5
                 :padding "5px"
                 :border-radius "6px"} :on {:click #(close-ahorn)}}"Click to Close"]
  (for [i (range 24)]
    [:span {:style {:position "fixed"
                    :top "-30px"
                    :left (str (+ 3 (rand 94)) "%")
                    :font-size "22px"
                    :animation (str "ahornFall " (+ 2.2 (rand 2.2)) "s linear infinite")
                    :animation-delay (str (rand 3) "s")
                    :z-index "1"}}
     "💰"])])
(r/render boss-div hornbox)

(defn start-ahorn []
(set! (.. (js/document.getElementById "hornboss") -style -opacity) "1"))
(defn close-ahorn []
(set! (.. (js/document.getElementById "hornboss") -style -opacity) "0")
(set! (.. (js/document.getElementById "hornboss") -style -display) "none"))
(def boss-stage (js/document.getElementById "hornbosses"))
(defn render-stage []
(set! (.. boss-stage -style -opacity) "0")
(js/setTimeout 
 (fn [] (do (r/render boss-stage (on-boss-render (nth @bosses @on-number)))
         (swap! on-number #(mod (inc %)(count @bosses))))) 200)
 (js/setTimeout #(set! (.. boss-stage -style -opacity) "1") 400))
(start-ahorn)
(render-stage)
(swap! bosses conj ["https://i.pravatar.cc/150?img=12" "NewUser" "Dynamic add demo" "07-25 10:18"]);;Add bosses
(js/setInterval
    (fn [] (render-stage))
    5000)



