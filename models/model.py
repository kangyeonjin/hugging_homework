from pydantic import BaseModel

#번역할 텍스트
class TranslationRequest(BaseModel):
    text: str