<h3>kaggle의 Body Signal of smoking 데이터 셋을 활용하여 시각화하고 머신러닝을 통해 흡연자를 예측한</h3>
<h2>Data Analysis Project</h2>

# Body signal of smoking 
Mini project to Data Analysis
From https://www.kaggle.com/datasets/kukuroo3/body-signal-of-smoking?resource=download

This dataset is a collection of basic health biological signal data.
The goal is to determine the presence or absence of smoking through bio-signals.

기본적인 건강 생체 신호 데이터의 모음입니다.
이 데이터를 분석함으로써 여러가지 생체 신호를 통해 흡연 여부를 판별하고자 하였습니다.

The dataset is divided into type.

1. entire dataset(smoking.csv)
2. the competition format(competition_format

#### columns
data shape : (55692, 27)

- ID : index
- gender
- age : 5-years gap
- height(cm)
- weight(kg)
- waist(cm) : Waist circumference length
- eyesight(left)
- eyesight(right)
- hearing(left)
- hearing(right)
- systolic : Blood pressure
- relaxation : Blood pressure
- fasting blood sugar
- Cholesterol : total
- triglyceride
- HDL : cholesterol type
- LDL : cholesterol type
- hemoglobin
- Urine protein
- serum creatinine
- AST : glutamic oxaloacetic transaminase type
- ALT : glutamic oxaloacetic transaminase type
- Gtp : γ-GTP
- oral : Oral Examination status
- dental caries
- tartar : tartar status
- smoking


저희는 라벨의 균형을 보기 위해 라벨인 흡연여부에 대해 파이차트를 이용하여 시각화 하였습니다.
라벨은 흡연이 36.73프로, 비흡연이 63.2755692프로로 이루어져있습니다. 
 또한 gender에 따른 흡연 비율도 파이차트를 이용하여 시각화하였습니다.

생체 신호 데이터를 사용하여 몇가지 질병에 따른 시각화를 진행하였습니다.
공복혈당 feature로 당뇨에 걸린 사람들을 파악할 수 있습니다.
당뇨에 따른 흡연 여부를 bar그래프로 시각화 하였으며 
60대 이상에서는 당뇨에 안걸린 사람의 흡연 비율이 더 높았고
20대 이상 60대 미만에서도 안걸린 사람의 흡연 비율이 더 높았습니다.

다음은 최고혈압 및 최저혈압으로 고혈압에 따른 흡연여부를 bar그래프로 시각화 하였습니다.
당뇨와 비슷하게 고혈압에서도 고혈압이 아닌 사람의 흡연 비율이 더 높았습니다.

마지막으로는 빈혈에 따른 흡연여부를 bar그래프로 시각화 하였습니다.
빈혈은 적혈구 수치 데이터로 파악 가능하며 남자와 여자 모두 빈혈이 걸리지 않은 사람의
흡연 비율이 더 높게 나왔습니다.

앞의 데이터 시각화들로 인해 당뇨, 고혈압, 빈혈은 흡연에 영향이 거의 없다는것을 알 수 있엇습니다.

다음은 데이터 전처리입니다.
데이터 분석을 하기 전 결측치확인, 데이터 인코더, 이상치 확인 3가지 방법으로 
데이터 전처리를 진행하였습니다

결측치 확인 결과 결측치는 없었고 feature oral은 값이 하나로만 이루어져 있어 삭제 하였습니다.
gender와 tartar feature은 object 타입으로 이루어져 있고 값이 두가지로만 이루어져있어서
원핫인코딩과 라벨 인코딩 상관이 없으므로  라벨 인코딩을 진행하였습니다.
트리모델을 제외한 다른 모델들을 학습할때는 feature간의 값들을 같은 범위의 값으로 바꿔주기 위해
Feature Scaling을 진행하였지만 트리모델을 학습할때는 정규화를 진행하지 않았습니다. 

이상치 파악을 위해 각 feature들을 boxplot으로 시각화 하였습니다.
보시면 모든 feature들에 이상치들이 많이 있는것을 확인할 수 있습니다.
저희는 이상치를 변환, 제거하거나 유지 시키는 방법으로 전처리를 진행하였고
자세한것은 밑에서 설명 드리겟습니다.

다음은 각 feature별 상관계수를 히트맵으로 시각화 한것입니다.
보시면 모든 feature들과 label과의 상관성이 높게 나타나지는 않습니다.

데이터분석으로 넘어가겟습니다. 
사용한 모델에 대해 설명드리기에 앞서 여러가지 모델들을 테스트해봤으며 그중 정확도가 높은
랜덤포레스트와 XGB를 이용하였습니다

첫번째로는 데이터 전처리 과정에서 이상치를 다른값으로 변환하여 학습을 진행한 결과입니다.
Feature Importance를 사용하여 상위권의 컬럼인 'GTP', 'triglyceride'의 이상치를 변환하였습니다.
백분위 90% 이상의 모든 값을 이상치로 판단하여 정상 범위로 추측되는 백분위 75%의 값으로 변환하였습니다. 
이상치를 판단한 근거로는 앞에서 본 column별 boxplot과 Describe함수를 사용하여 나온 값을 보고 
판단하였습니다.
이상치를 변환 후 Describe함수를 다시 사용하여 이상치가 사라지고 값들의
범주가 의학적으로도 정상적인 범주 내에 도달하는 것을 보고 데이터 분석을 진행하였습니다
랜덤 포레스트를 사용하였을때는 test셋에서 정확도가 0.89가 나왔고
XGB에서는 정확도가 0.81이 나왔습니다.

두번째는 이상치를 삭제하고 학습을 진행한 결과입니다.
IQR값을 이용해 범위를 넘어가는 이상치를 삭제하였습니다.
랜덤 포레스트를 사용하였을때는 test셋에서 정확도가 0.82가 나왔고
XGB에서는 정확도가 0.81 나왔습니다.

세번째는 이상치를 유지한채 분석을 학습을 진행한 결과입니다
랜덤 포레스트 test셋 정확도가 0.81이 나왔고 XGB에서는 0.77이 나왔습니다

다음 bar그래프는 이상치 처리 방법 별 정확도를 한눈에 보기 편하도록 표현한 것 입니다.
보시면 이상치를 유지하는것보다 제거하는것이 정확도가 더 높게 나왔고 
이상치를 정상범위의 값으로 변환시키는것이 정확도가 제일 높게 나왔습니다

가장 정확도가 높게나온 이상치를 변환하여 학습한 랜덤포레스트의 ROC curve 그래프입니다.
AUC점수가 0.96로 분류성능이 아주 좋은것을 볼수가 있습니다.

마지막으로 가장 성능이 잘 나온 랜덤포레스트 모델을 통해 각 feature들간의 중요도를 그래프로 나타냈습니다.
저희는 흡연을 판단하기에는 치아와 관련된 컬럼인 tartar(치석)와 dental caries(충치여부)가 
중요할거라고 생각하였지만 gender의 중요도가 가장 높게 나타났습니다. 
이는 여성의 95%가 비흡연인 것이 원인이 되었다고 생각합니다.
간, 신장과 관련된 질병이 흡연과 연관성이 낮게 나타났고 그래서 연 유무에 직접적인 영향을 끼치는
질병에 대한 데이터들이 있었다면 예측 더욱 도움이 되었을 것이라고 생각합니다.

for Data_Analysis_Project of playdata

  
  
  
출처 : https://www.kaggle.com/datasets/kukuroo3/body-signal-of-smoking
